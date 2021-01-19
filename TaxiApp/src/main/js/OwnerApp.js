import React from "react";
import {
    Route,
    Switch,
    Link,
    Redirect
} from 'react-router-dom';

import {InputForm, ItemTable} from './common/Compenents';
import axios from "axios";


// class AcceptDeclineCV extends React.Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             cvId: "",
//             carId: ""
//         }
//     }
//
//     handleChange(event) {
//         const target = event.target;
//         const value = target.value;
//         const name = target.name;
//         this.setState({
//             [name]: value
//         });
//     }
//
//     // handleAccept(event) {
//     //     if (this.state.cvId== "" || this.state.carId === "") {
//     //         alert("Fill cvId and carId!")
//     //     } else {
//     //         event.preventDefault();
//     //         axios.get("/api/owner/cv/" + this.state.cvId + "/ack/" + this.state.carId)
//     //             .then((response) => {
//     //                 this.setState({cvId: "", carId: ""})
//     //                 console.log("Accepted!");
//     //             })
//     //             .catch((error) => {
//     //                 console.log(error)
//     //             })
//     //     }
//     // }
//     //
//     // handleDecline(event) {
//     //     if (this.state.cvId === "") {
//     //         alert("Fill cvId!")
//     //     } else {
//     //         event.preventDefault();
//     //         axios.get("/api/owner/cv/" + this.state.cvId + "/nack")
//     //             .then((response) => {
//     //                 this.setState({cvId: "", carId: ""})
//     //                 console.log("Declined!");
//     //             })
//     //             .catch((error) => {
//     //                 console.log(error.response.status);
//     //                 console.log("Error!");
//     //                 alert(error.response)
//     //             })
//     //     }
//     // }
//
//     render() {
//         return (
//             <div className="AcceptDecline">
//                 <input
//                     type="text"
//                     name="cvId"
//                     placeholder="cvId"
//                     onChange={(e) => this.handleChange(e)}/>
//                 <input
//                     type="text"
//                     name="carId"
//                     placeholder="carId"
//                     onChange={(e) => this.handleChange(e)}/>
//                 <button type="submit" name="accept" onClick={(e) => this.handleAccept(e)}>Accept</button>
//                 <button type="submit" name="decline" onClick={(e) => this.handleDecline(e)}>Decline</button>
//             </div>
//         )
//     }
//
// }


class OwnerApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            cars: [],
            cvs: [],
            drivers: [],
            chosenCarId: "",
            chosenCvId: ""
        }
    }

    getCars() {
        axios.get("/api/owner/cars")
            .then((response) => {
                const cars = response.data;
                console.log("GET CARS: ", cars)
                this.setState({
                    chosenCarId: this.state.chosenCarId,
                    chosenCvId: this.state.chosenCvId,
                    messages: this.state.messages,
                    cars: cars,
                    cvs: this.state.cvs,
                    drivers: this.state.drivers
                })
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getMessages() {
        axios.get("/api/owner/messages")
            .then((response) => {
                const messages = response.data;
                this.setState({
                    chosenCarId: this.state.chosenCarId,
                    chosenCvId: this.state.chosenCvId,
                    messages: messages,
                    cars: this.state.cars,
                    cvs: this.state.cvs,
                    drivers: this.state.drivers
                })
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getCVs() {
        axios.get("/api/owner/cvs")
            .then((response) => {
                const cvs = response.data;
                this.setState({
                    chosenCarId: this.state.chosenCarId,
                    chosenCvId: this.state.chosenCvId,
                    messages: this.state.messages,
                    cars: this.state.cars,
                    cvs: cvs,
                    drivers: this.state.drivers
                })
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getDrivers() {
        console.log("GET DRIVERS")
        axios.get("/api/owner/drivers")
            .then((response) => {
                const drivers = response.data;
                console.log(drivers)
                this.setState({
                    chosenCarId: this.state.chosenCarId,
                    chosenCvId: this.state.chosenCvId,
                    messages: this.state.messages,
                    cars: this.state.cars,
                    cvs: this.state.cvs,
                    drivers: drivers
                })
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    componentDidMount() {
        this.getCars();
        this.getMessages();
        this.getCVs();
        this.getDrivers();
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleAccept(event) {
        if (this.state.chosenCvId === "" || this.state.chosenCarId === "") {
            alert("Fill cvId and carId!")
        } else {
            event.preventDefault();
            axios.get("/api/owner/cv/" + this.state.chosenCvId + "/ack/" + this.state.chosenCarId)
                .then((response) => {
                    this.setState({chosenCvId: "", chosenCarId: "",
                        messages: this.state.messages,
                        cars: this.state.cars,
                        cvs: this.state.cvs,
                        drivers: this.state.drivers
                    })
                    this.getCVs();
                    this.getMessages();
                    console.log("Accepted!");
                })
                .catch((error) => {
                    alert(error.response.data.error)
                })
        }
    }

    handleDecline(event) {
        if (this.state.chosenCvId === "") {
            alert("Fill cvId!")
        } else {
            event.preventDefault();
            axios.get("/api/owner/cv/" + this.state.chosenCvId + "/nack")
                .then((response) => {
                    this.setState({chosenCvId: "", chosenCarId: "",
                        messages: this.state.messages,
                        cars: this.state.cars,
                        cvs: this.state.cvs,
                        drivers: this.state.drivers
                    })
                    this.getCVs();
                    this.getMessages();
                    console.log("Declined!");
                })
                .catch((error) => {
                    alert(error.response.data.error)
                })
        }
    }

    makeCarsTable(cars) {
        let data = cars.map(c => {
            let item = {
                key: c.id,
                values: [c.id, c.licensePlate, c.model]
            }
            return item
        })
        return (
            <ItemTable
                header={["Id", "License Plate", "Model"]}
                data={data}
            />
        )
    }

    makeDriversTable(drivers) {
        let data = drivers.map(o => {
            return {
                key: o.id,
                values: [o.id, o.name, o.surname, o.phone, o.active, o.carId, o.carInfo]
            }
        })
        return (
            <ItemTable
                header={["Id", "Name", "Surname", "Phone", "Activation", "Car Id", "Car info"]}
                data={data}
            />
        )
    }

    makeMessagesTable(messages) {
        let data = messages.map(m => {
            let item = {
                key: m.id,
                values: [m.from, m.to, m.type, m.payload === null ? "" : m.payload]
            }
            return item
        })
        return (
            <ItemTable
                header={["From", "To", "Content", "Payload"]}
                data={data}
            />
        )
    }

    makeCVsTable(cvs) {
        let data = cvs.map(cv => {
            let item = {
                key: cv.id,
                values: [cv.id, cv.name, cv.surname, cv.phone, cv.experience, cv.shown ? "yes" : "no"]
            }
            return item
        })
        return (
            <ItemTable
                header={["Id", "Name", "Surname", "Phone", "Experience", "Answered"]}
                data={data}
            />
        )
    }

    render() {
        return (
            <div>
                Hello, {this.props.name} {this.props.surname}! You are owner
                <hr />
                <h2> My cars </h2>
                {this.makeCarsTable(this.state.cars)}
                <h2> Drivers </h2>
                {this.makeDriversTable(this.state.drivers)}
                <h2> Messages </h2>
                {this.makeMessagesTable(this.state.messages)}
                <h2> CVs </h2>
                {this.makeCVsTable(this.state.cvs)}
                <div className="AcceptDecline">
                    <input
                        type="text"
                        name="chosenCvId"
                        placeholder="cvId"
                        onChange={(e) => this.handleChange(e)}/>
                    <input
                        type="text"
                        name="chosenCarId"
                        placeholder="carId"
                        onChange={(e) => this.handleChange(e)}/>
                    <button type="submit" name="accept" onClick={(e) => this.handleAccept(e)}>Accept</button>
                    <button type="submit" name="decline" onClick={(e) => this.handleDecline(e)}>Decline</button>
                </div>
            </div>
        )
    }
}

export default OwnerApp;
