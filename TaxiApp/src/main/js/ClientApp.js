import React from "react";
import {
    Route,
    Switch,
    Link,
    Redirect
} from 'react-router-dom';

import {InputForm, ItemTable} from './common/Compenents';
import axios from "axios";

class ClientApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            orders: []
        }
    }

    getOrders() {
        axios.get("/api/client/orders")
            .then((response) => {
                const orders = response.data;
                this.setState({ orders: orders, messages: this.state.messages})
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getMessages() {
        axios.get("/api/client/messages")
            .then((response) => {
                const messages = response.data;
                console.log(messages)
                this.setState({ orders: this.state.orders, messages: messages})
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    componentDidMount() {
        this.getOrders();
        this.getMessages();
    }

    submitCV(o) {
        if (o.CVName === "" || o.CVSurname === "" || o.CVPhone === "" || o.CVExperience === "") {
            alert("Fill all CV fields");
        } else {
            axios.post("/api/client/cv", o)
                .then((response) => {
                    const data = response.data;
                    console.log(data);
                    this.getMessages();
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    }

    submitOrder(o) {
        if (o.srcAddress === "" || o.dstAddress === "") {
            alert("Fill Source and Destination addresses");
        } else {
            axios.post("/api/client/order", o)
                .then((response) => {
                    const data = response.data;
                    console.log("SUBMIT ORDER", data);
                    this.getOrders();
                    this.getMessages();
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    }

    makeOrderForm() {
        return (
            <div className="orderForm">
                <InputForm rows={[
                    {rowName: "srcAddress", rowPlaceholder: "Source Address"},
                    {rowName: "dstAddress", rowPlaceholder: "Destination Address"},
                ]}
                initState={{srcAddress: "", dstAddress: ""}}
                onSubmit={(o) => this.submitOrder(o)}
                />
            </div>
        )
    }

    makeCVForm() {
        return (
            <div className="CVForm">
                <InputForm rows={[
                    {rowName: "name", rowPlaceholder: "Name"},
                    {rowName: "surname", rowPlaceholder: "Surname"},
                    {rowName: "phone", rowPlaceholder: "Phone"},
                    {rowName: "experience", rowPlaceholder: "Experience"},
                ]}
                initState={{name: "", surname: "", phone: "", experience: ""}}
                onSubmit={(o) => this.submitCV(o)}/>
            </div>
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
                values: [o.id, o.dstAddress, o.srcAddress, o.status]
            }
        })
        return (
            <ItemTable
                header={["Id", "Source", "Destination", "Status"]}
                data={data}
            />
        )
    }

    render() {
        return (
            <div>
                Hello, {this.props.name} {this.props.surname}! You are client
                <hr />
                <h2> Create order </h2>
                {this.makeOrderForm()}
                <h2> Create CV </h2>
                {this.makeCVForm()}
                <h2> Messages </h2>
                {this.makeMessagesTable(this.state.messages)}
                <h2> My orders </h2>
                {this.makeOrders(this.state.orders)}
            </div>
        )
    }
}

export default ClientApp;
