import React from 'react';
import axios from "axios";


class InputForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {...props.initState};
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.onSubmit(this.state)
    }

    render() {
        const rows = this.props.rows.map(row => {
            return <li><input
                type="text"
                name={row.rowName}
                placeholder={row.rowPlaceholder}
                onChange={(e) => this.handleChange(e)}/></li>
        })
        return (
            <form onSubmit={(e) => this.handleSubmit(e)}>
                <fieldset>
                {rows}
                <div className="submit">
                    <button type="submit" name="create">Submit</button>
                </div>
                </fieldset>
            </form>
        )
    }
}


class ItemTable extends React.Component {
    constructor(props) {
        super(props);
    }

    renderHeader() {
        const columns = this.props.header.map((f, id) => {
            return <th key={id}>{f}</th>
        })
        return (
            <tr>
                {columns}
            </tr>
        )
    }

    renderBody() {
        return this.props.data.map(data => {
            const row = data.values.map((value, id) => {
                return <td key={id}>{value}</td>
            })
            return (
                <tr key={data.key}>
                    {row}
                </tr>
            )
        });
    }

    render() {
        return (
            <table>
                <thead>
                    {this.renderHeader()}
                </thead>
                <tbody>
                    {this.renderBody()}
                </tbody>
            </table>
        )
    }

}


class AcceptDecline extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            value: ""
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        this.setState({
            value: value
        });
    }

    handleAccept(event) {
        event.preventDefault();
        this.props.onAccept(this.state)
    }

    handleDecline(event) {
        event.preventDefault();
        this.props.onDecline(this.state)
    }

    render() {
        return (
            <div className="AcceptDecline">
                <input
                    type="text"
                    name={this.props.name}
                    placeholder={this.props.placeholder}
                    onChange={(e) => this.handleChange(e)}/>
                <button type="submit" name="accept" onClick={(e) => this.handleAccept(e)}>Accept</button>
                <button type="submit" name="decline" onClick={(e) => this.handleDecline(e)}>Decline</button>
            </div>
        )
    }
}

class LogoutButton extends React.Component {

    handleLogout() {
        axios
    }

    render() {
        return (
            <button
                type="submit"
                name="logout"
                onClick={(e) => this.handleLogout()}>Logout</button>
        )
    }
}

export {InputForm, ItemTable, AcceptDecline}