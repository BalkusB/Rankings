import React from 'react';

class ranking extends React.Component
{
    state = { data: [], id: '', victor: '', loser: ''};

    componentDidMount() {
        fetch('http://localhost:8080/rankings')
            .then(response => response.json())
            .then(data => this.setState({ data }))
    }

    render() {
        var tableStyle = {
            "border": "1px solid black",
            "width": "95%"
        };
        var headerStyle = {
            "border": "1px solid black",
        };
        var dataStyle = {
            "border": "1px solid black",
        };
        return (
            <React.Fragment>
                <div className="row">
                    <div className="col-2">
                        <img src="https://cdn.discordapp.com/attachments/428724450050048012/697501757655351377/UBA_2.png"
                            alt="" width="120" height="108"/>
                    </div>
                    <div className="col">
                        <br></br>
                        <br></br>
                        <label htmlFor="id">Battle ID: &nbsp;</label>
                        <input type="text" id={this.state.id} name="id" size="20"
                            value={this.state.id} onChange={evt => this.updateId(evt)} />
                        <label htmlFor="victor">&nbsp; &nbsp; Victor: &nbsp;</label>
                        <input type="text" id={this.state.victor} name="victor" size="20"
                            value={this.state.victor} onChange={evt => this.updateVictor(evt)} />
                        <label htmlFor="loser">&nbsp; &nbsp; Loser :&nbsp;</label>
                        <input type="text" id={this.state.loser} name="loser" size="20"
                            value={this.state.loser} onChange={evt => this.updateLoser(evt)} />
                        <button onClick={() => this.addNewBattle()}>Add Battle</button>
                    </div>
                </div>
                <center>
                    <div>
                        <table style={tableStyle}>
                            <thead>
                                <tr>
                                    <th style={headerStyle}>Player</th>
                                    <th style={headerStyle}>Elo</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.data.map(el => (
                                    <tr key={el.name}>
                                        <td style={dataStyle}>{el.name}</td>
                                        <td style={dataStyle}>{el.elo}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        </div>
                    </center>
            </React.Fragment>
        );
    }

    updateId(evt) {
        this.setState({
            id: evt.target.value
        });
    }

    updateVictor(evt) {
        this.setState({
            victor: evt.target.value
        });
    }

    updateLoser(evt) {
        this.setState({
            loser: evt.target.value
        });
    }

    addNewBattle() {
        const battle = {id: this.state.id, victor: this.state.victor, loser: this.state.loser};
        const battleJSON = JSON.stringify(battle);
        console.log(battleJSON)

        fetch('http://localhost:8080/rankings', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: battleJSON
        }).then(response => response.json())
        window.location.reload(false);
    }
}

export default ranking;