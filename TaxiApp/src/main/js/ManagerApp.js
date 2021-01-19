import React from "react";
import {
    Route,
    Switch,
    Link,
    Redirect
} from 'react-router-dom';

import {InputForm, ItemTable, AcceptDecline} from './common/Compenents';
import axios from "axios";


class AssignOrderToDriver extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            driverId: "",
            orderId: ""
        }
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
        event.preventDefault();
        this.props.onAccept(this.state)
    }

    render() {
        return (
            <div className="AssignOrderToDriver">
                <input
                    type="text"
                    name="driverId"
                    placeholder="Driver Id"
                    onChange={(e) => this.handleChange(e)}/>
                <input
                    type="text"
                    name="orderId"
                    placeholder="Order Id"
                    onChange={(e) => this.handleChange(e)}/>
                <button type="submit" name="send" onClick={(e) => this.handleAccept(e)}>Send</button>
            </div>
        )
    }
}

class DriverActivation extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            driverId: ""
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            driverId: value
        });
    }

    handleAccept(event) {
        event.preventDefault();
        this.props.onAccept(this.state)
    }

    render() {
        return (
            <div className="DriverActivation">
                <input
                    type="text"
                    name="driverId"
                    placeholder="Driver Id"
                    onChange={(e) => this.handleChange(e)}/>
                <button type="submit" name="activate" onClick={(e) => this.handleAccept(e)}>Activate</button>
            </div>
        )
    }
}


class ManagerApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            orders: [],
            drivers: [],
        }
    }

    getOrders() {
        console.log("GET ORDERS")
        axios.get("/api/manager/orders")
            .then((response) => {
                const orders = response.data;
                console.log(orders);
                this.setState({
                    messages: this.state.messages,
                    orders: orders,
                    drivers: this.state.drivers})
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getMessages() {
        console.log("GET MESSAGES")
        axios.get("/api/manager/messages")
            .then((response) => {
                const messages = response.data;
                console.log(messages)
                this.setState({
                    messages: messages,
                    orders: this.state.orders,
                    drivers: this.state.drivers})
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getDrivers() {
        console.log("GET DRIVERS")
        axios.get("/api/manager/drivers")
            .then((response) => {
                const drivers = response.data;
                console.log(drivers)
                this.setState({
                    messages: this.state.messages,
                    orders: this.state.orders,
                    drivers: drivers})
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    componentDidMount() {
        this.getOrders();
        this.getMessages();
        this.getDrivers();
    }

    handleDriverAssign(orderId, driverId) {
        if (orderId === "" || driverId === "") {
            alert("Fill 'Order Id' and 'Driver Id'!");
        } else {
            axios.get("/api/manager/order/" + orderId + "/driver/" + driverId)
                .then((response) => {
                    const data = response.data;
                    console.log(data)
                    this.getMessages();
                })
                .catch(function (error) {
                    alert(error.response.data.error)
                })
        }
    }

    makeDriverAssign(orderId, driverId) {
        return (
            <AssignOrderToDriver
                onAccept={(o) => this.handleDriverAssign(o.orderId, o.driverId)}/>
        )
    }

    handleOrderAccept(path, id, solution) {
        if (id === "") {
            alert("Fill field!");
        } else {
            axios.get("/api/manager/" + path + "/" + id + "/" + solution)
                .then((response) => {
                    const data = response.data;
                    console.log(data)
                    this.getMessages();
                })
                .catch(function (error) {
                    alert(error.response.data.error)
                })
        }
    }

    makeDriverOrderAcceptDecline() {
        return (
            <AcceptDecline
                name="DriverId"
                placeholder="Driver Id"
                onAccept={(o) => { this.handleOrderAccept("driver", o.value, "ACK")} }
                onDecline={(o) => { this.handleOrderAccept("driver", o.value, "NACK")} } />
        )
    }

    makeClientOrderAcceptDecline() {
        return (
            <AcceptDecline
                name="OrderId"
                placeholder="Order Id"
                onAccept={(o) => { this.handleOrderAccept("order", o.value, "ACK")} }
                onDecline={(o) => { this.handleOrderAccept("order", o.value, "NACK")} } />
        )
    }

    handleDriverActivation(driverId) {
        if (driverId === "") {
            alert("Fill 'Driver Id'!");
        } else {
            axios.get("/api/manager/activate/" + driverId)
                .then((response) => {
                    const data = response.data;
                    console.log(data)
                    this.getMessages();
                    this.getDrivers();
                })
                .catch(function (error) {
                    alert(error.response.data.error)
                })
        }
    }

    makeDriverActivation() {
        return (
            <DriverActivation
                onAccept={(o) => {this.handleDriverActivation(o.driverId)}} />
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

    makeOrders(orders) {
        let data = orders.map(o => {
            return {
                key: o.id,
                values: [o.id, o.idClient, o.dstAddress, o.srcAddress, o.status]
            }
        })
        return (
            <ItemTable
                header={["Id", "Client Id", "Source", "Destination", "Status"]}
                data={data}
            />
        )
    }

    makeDrivers(drivers) {
        let data = drivers.map(o => {
            return {
                key: o.id,
                values: [o.id, o.name, o.surname, o.phone, o.status]
            }
        })
        return (
            <ItemTable
                header={["Id", "Name", "Surname", "Phone", "Status"]}
                data={data}
            />
        )
    }

    render() {
        return (
            <div>
                Hello, {this.props.name} {this.props.surname}! You are manager
                <hr />
                <h2> Messages </h2>
                {this.makeMessagesTable(this.state.messages)}
                <h2> Orders </h2>
                {this.makeOrders(this.state.orders)}
                <h2> Drivers </h2>
                {this.makeDrivers(this.state.drivers)}
                <h2> Send order to driver </h2>
                {this.makeDriverAssign()}
                <h2> Driver </h2>
                {this.makeDriverOrderAcceptDecline()}
                <h2> Client </h2>
                {this.makeClientOrderAcceptDecline()}
                <h2> Activate Driver </h2>
                {this.makeDriverActivation()}
            </div>
        )
    }
}

export default ManagerApp;
