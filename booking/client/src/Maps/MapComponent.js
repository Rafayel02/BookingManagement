import React, { Component } from 'react'

import { Map, GoogleApiWrapper } from 'google-maps-react';

export class MapComponent extends Component {

    render() {

        return (<div className="map-area">

                <Map

                    google={this.props.google}

                    zoom={14}

                    center={{

                        lat: 47.444,

                        lng: -122.176

                    }}

                ></Map>

            </div>);

    }

}

export default GoogleApiWrapper({

    apiKey: ('AIzaSyArFTpU0FOqVbNuDxScA0p0O2CGSd4Josc')

})(MapComponent);