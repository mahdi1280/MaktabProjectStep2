import React, { useState } from 'react'

import MapPicker from 'react-google-map-picker'

const DefaultLocation = { lat: 31.94855258523951, lng: 54.27612663736113};
const DefaultZoom = 6;

export default function Map({setLongitute,setLatitute,longitute,latitute}){

    const [defaultLocation, setDefaultLocation] = useState(DefaultLocation);

    const [location, setLocation] = useState(defaultLocation);
    const [zoom, setZoom] = useState(DefaultZoom);

    function handleChangeLocation (lat, lng){
        setLocation({lat:lat, lng:lng});
    }

    function handleChangeZoom (newZoom){
        setZoom(newZoom);
    }

    function handleResetLocation(){
        setDefaultLocation({ ... DefaultLocation});
        setZoom(DefaultZoom);
    }

    return (
        <>
            <button onClick={handleResetLocation}>Reset Location</button>
            <label>Latitute:</label><input onChange={(e)=>setLatitute(e.target.value)} type='text' value={location.lat} disabled/>
            <label>Longitute:</label><input onChange={(e)=>setLongitute(e.target.value)} type='text' value={location.lng} disabled/>
            <label>Zoom:</label><input type='text' value={zoom} disabled/>

            <MapPicker defaultLocation={defaultLocation}
                       zoom={zoom}
                       mapTypeId="roadmap"
                       style={{height:'400px'}}
                       onChangeLocation={handleChangeLocation}
                       onChangeZoom={handleChangeZoom}
                       apiKey='AIzaSyD07E1VvpsN_0FvsmKAj4nK9GnLq-9jtj8'/>
        </>
    );
}