package ir.maktab.maktabprojectstep2.service.temp;

import ir.maktab.maktabprojectstep2.core.ErrorMessage;
import ir.maktab.maktabprojectstep2.core.RuleException;
import ir.maktab.maktabprojectstep2.dto.request.ExpertSaveRequest;
import ir.maktab.maktabprojectstep2.dto.request.UserCustomerSaveRequest;
import ir.maktab.maktabprojectstep2.model.TempUser;
import ir.maktab.maktabprojectstep2.model.enums.Role;
import ir.maktab.maktabprojectstep2.repository.TempUserRepository;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class TempServiceImpl implements TempService {

    public static final Sort SORT = Sort.by(Sort.Direction.DESC,"createdAt");


    private final TempUserRepository tempUserRepository;
    private final Random random;
    private final MessageSourceAccessor messageSourceAccessor;
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    public TempServiceImpl(TempUserRepository tempUserRepository, MessageSourceAccessor messageSourceAccessor, JavaMailSender emailSender, PasswordEncoder passwordEncoder) {
        this.tempUserRepository = tempUserRepository;
        this.messageSourceAccessor = messageSourceAccessor;
        this.emailSender = emailSender;
        this.passwordEncoder = passwordEncoder;
        this.random = new SecureRandom();
    }

    @Override
    public Optional<TempUser> findByEmail(String email) {
        return tempUserRepository.findTopByEmail(email,SORT);
    }

    @Override
    public TempUser saveAndSendEmail(UserCustomerSaveRequest userCustomerSaveRequest) {
        int verifyCode = Integer.parseInt(String.format("%06d", random.nextInt(999999)));
        sendSimpleMessage(userCustomerSaveRequest.getEmail(), messageSourceAccessor.getMessage("email.subject")
                , String.format(messageSourceAccessor.getMessage("email.text"), verifyCode));
        TempUser tempUser = createTempUser(userCustomerSaveRequest, String.valueOf(verifyCode));
        return tempUserRepository.save(tempUser);
    }

    @Override
    public TempUser saveAndSendEmail(ExpertSaveRequest expertSaveRequest) {
        int verifyCode = Integer.parseInt(String.format("%06d", random.nextInt(999999)));
        sendSimpleMessage(expertSaveRequest.getEmail(), messageSourceAccessor.getMessage("email.subject")
                , String.format(messageSourceAccessor.getMessage("email.text"), verifyCode));
        TempUser tempUser = createTempUser(expertSaveRequest, String.valueOf(verifyCode));
        return tempUserRepository.save(tempUser);
    }

    public void sendSimpleMessage(
            String to, String subject, String text) {
        MimeMessage msg = emailSender.createMimeMessage();

        MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(msg, true);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text, true);
        } catch (MessagingException e) {
            throw new RuleException(ErrorMessage.error("send.email.error"));
        }
        emailSender.send(msg);
    }

    private TempUser createTempUser(UserCustomerSaveRequest userSaveRequest, String verifyCode) {
        return TempUser.builder()
                .firstname(userSaveRequest.getFirstname())
                .lastname(userSaveRequest.getLastname())
                .password(userSaveRequest.getPassword())
                .email(userSaveRequest.getEmail())
                .verifyCode(passwordEncoder.encode(verifyCode))
                .expireDate(LocalDateTime.now().plusMinutes(3))
                .tryCount(0)
                .role(Role.CUSTOMER)
                .build();
    }

    private TempUser createTempUser(ExpertSaveRequest expertSaveRequest, String verifyCode) {
        try {
            return TempUser.builder()
                    .firstname(expertSaveRequest.getFirstname())
                    .lastname(expertSaveRequest.getLastname())
                    .password(expertSaveRequest.getPassword())
                    .email(expertSaveRequest.getEmail())
                    .verifyCode(passwordEncoder.encode(verifyCode))
                    .expireDate(LocalDateTime.now().plusMinutes(3))
                    .tryCount(0)
                    .role(Role.CUSTOMER)
                    .image(expertSaveRequest.getMultipartFile().getBytes())
                    .build();
        } catch (IOException e) {
            throw new RuleException(ErrorMessage.error("{image.not.valid}"));
        }
    }
}
