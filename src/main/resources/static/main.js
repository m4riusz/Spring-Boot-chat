/**
 * Created by mariusz on 09.02.17.
 */
new Vue({
    el: '#main',
    data: {
        welcome: "Welcome to simple chat application",
        connected: false,
        client: {},
        users: [],
        message: "",
        messages: [
            {text: "kek", owner: "Keksimus Maximus"},
            {text: "kek1", owner: "Keksimus Maximus"},
            {text: "kek2", owner: "Keksimus Maximus"},
            {text: "kek3", owner: "Keksimus Maximus"},
            {text: "kek4", owner: "Keksimus Maximus"},
            {text: "kek5", owner: "Keksimus Maximus"},
            {text: "kek6", owner: "Keksimus Maximus"},
            {text: "kek7", owner: "Keksimus Maximus"},
            {text: "kek8", owner: "Keksimus Maximus"},
            {text: "kek9", owner: "Keksimus Maximus"},
            {text: "kek10", owner: "Keksimus Maximus"},
            {text: "kek11", owner: "Keksimus Maximus"}
        ]
    },
    methods: {
        connect() {
            let websocket = new SockJS("/ws");
            this.client = Stomp.over(websocket);
            this.client.connect({}, (frame) => {
                this.connected = true;
                this.subscribeToUsers();
                this.client.send("/app/chat.login", {}, "");
            });
        },

        disconnect() {
            this.client.send("/app/chat.logout", {}, "");
            this.client.disconnect(() => {
                this.connected = false;
            });

        },

        subscribeToUsers() {
            this.client.subscribe("/app/chat.users", (response) => {
                this.users = JSON.parse(response.body);
            });
            this.client.subscribe("/chat.login", (response) => {
                this.users.push(JSON.parse(response.body));
            });

            this.client.subscribe("/chat.logout", (response) => {
                this.users = this.users.filter(user => user.username != JSON.parse(response.body).username);
            });
        },

        send(){
            this.messages.push({text: this.message, owner: "ICH"});
        }
    }
});