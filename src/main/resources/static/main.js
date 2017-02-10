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
        messages: []
    },
    methods: {
        connect() {
            let websocket = new SockJS("/ws");
            this.client = Stomp.over(websocket);
            this.client.connect({}, (frame) => {
                this.connected = true;
                this.subscribeToUsers();
                this.subscribeToMessages();
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

        subscribeToMessages(){
            this.client.subscribe("/app/chat.message.all", (response) => {
                this.messages = JSON.parse(response.body);
            });
            this.client.subscribe("/chat.message.new", (response) => {
                this.messages.push(JSON.parse(response.body));
            })
        },

        send(){
            this.client.send("/app/chat.message.create", {}, JSON.stringify({content: this.message}));
        }
    }
});