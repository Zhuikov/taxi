import React from "react";
import {
    Route,
    Switch,
    Link,
    Redirect
} from 'react-router-dom';

import {InputForm, ItemTable} from './common/Compenents';
import axios from "axios";

class DriverApp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            currentSrcAddress: "",
            currentDstAddress: "",
            idOrder: ""
        }
    }

    getMessages() {
        axios.get("/api/driver/messages")
            .then((response) => {
                const messages = response.data;
                console.log(messages)
                this.setState({
                    messages: messages,
                    currentSrcAddress: this.state.currentSrcAddress,
                    currentDstAddress: this.state.currentDstAddress,
                    idOrder: this.state.idOrder
                })
            })
            .catch(function (error) {
                console.log(error)
            })
    }

    getCurrentOrder() {
        axios.get("/api/driver/currentOrder")
            .then((response) => {
                const order = response.data;
                if (order === null) {
                    this.setState({
                        messages: this.state.messages,
                        currentSrcAddress: "",
                        currentDstAddress: "",
                        idOrder: ""
                    })
                } else {
                    this.setState({
                        messages: this.state.messages,
                        currentSrcAddress: order.srcAddress,
                        currentDstAddress: order.dstAddress,
                        idOrder: order.id
                    })
                }
            })
            .catch(function (error) {
                console.log(error)
            })

    }

    componentDidMount() {
        this.getMessages();
        this.getCurrentOrder();
    }

    handleSetOrder(idOrder) {
        if (idOrder === "") {
            alert("Set Order Id");
        } else {
            axios.get("/api/driver/set/" + idOrder)
                .then((response) => {
                    const data = response.data;
                    this.getMessages();
                    this.setState({
                        messages: this.state.messages,
                        currentSrcAddress: data.srcAddress,
                        currentDstAddress: data.dstAddress,
                        idOrder: data.id
                    })
                })
                .catch((error) => {
                    alert(error.response.data.error)
                })
        }
    }

    makeSetOrderForm() {
        return (<div className="setOrderForm">
                <InputForm rows={[
                    {rowName: "idOrder", rowPlaceholder: "Order Id"},
                ]}
                initState={{idOrder: ""}}
                onSubmit={(o) => this.handleSetOrder(o.idOrder)}/>
            </div>
        )
    }

    handleAcceptOrder(solution) {
        axios.get("/api/driver/" + solution)
            .then((response) => {
                const data = response.data;
                this.getMessages();
            })
            .catch((error) => {
                console.log(error)
            })
    }

    makeAcceptDeclineButtons() {
        return (
            <div>
                <button
                    type="submit"
                    name="accept"
                    onClick={() => this.handleAcceptOrder("ACK")}
                >Accept Order</button>
                <button
                    type="submit"
                    name="decline"
                    onClick={() => this.handleAcceptOrder("NACK")}
                >Decline Order</button>
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

    makeCurrentOrderTable() {
        return (
            <table>
                <thead>
                    <tr>
                        <th>Order Id</th>
                        <th>Source address</th>
                        <th>Destination address</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{this.state.idOrder}</td>
                        <td>{this.state.currentSrcAddress}</td>
                        <td>{this.state.currentDstAddress}</td>
                    </tr>
                </tbody>
            </table>
        )
    }

    handleFinishOrder(idOrder) {
        if (idOrder === "") {
            alert("You have not active order")
        } else {
            axios.get("/api/driver/finish/" + idOrder)
                .then((response) => {
                    const data = response.data;
                    this.getMessages();
                    this.setState({
                        messages: this.state.messages,
                        currentSrcAddress: "",
                        currentDstAddress: "",
                        idOrder: ""
                    })
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    }

    makeFinishOrderButton() {
        return (
            <div>
                <button
                    type="submit"
                    name="finishOrder"
                    onClick={() => this.handleFinishOrder(this.state.idOrder)}
                >Finish Order</button>
            </div>
        )
    }

    render() {
        return (
            <div>
                Hello, {this.props.name} {this.props.surname}! You are driver
                <hr />
                <h2> Messages </h2>
                {this.makeMessagesTable(this.state.messages)}
                {this.makeAcceptDeclineButtons()}
                <h2> Set order </h2>
                {this.makeSetOrderForm()}
                <h2> Current Order </h2>
                {this.makeCurrentOrderTable()}
                <h2> Finish Order </h2>
                {this.makeFinishOrderButton()}
            </div>
        )
    }
}

export default DriverApp;
