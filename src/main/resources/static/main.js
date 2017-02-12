/**
 * Created by mariusz on 09.02.17.
 */
let main = new Vue({
    el: '#main',
    data: {
        welcome: "Welcome to simple chat application",
        currentUser: {},
        connected: false,
        client: {},
        users: [],
        message: "",
        messages: [],
        error: ''
    },
    updated(){
        this.scrollDownChat();
    },
    methods: {
        connect() {
            let websocket = new SockJS("/ws");
            this.client = Stomp.over(websocket);
            this.client.connect({}, (frame) => {
                this.connected = true;
                this.subscribeToUsers();
                this.subscribeToMessages();
            });
        },

        disconnect() {
            this.client.disconnect(() => {
                this.connected = false;
            });
        },

        subscribeToUsers() {
            this.client.subscribe("/app/chat.users", (response) => {
                this.users = JSON.parse(response.body);
            });
            this.client.subscribe("/chat.login", (response) => {
                this.currentUser = JSON.parse(response.body);
                this.users.push(this.currentUser);
                this.subscribeToError();

            });
            this.client.subscribe("/chat.logout", (response) => {
                this.users = this.users.filter(user => user.username != JSON.parse(response.body).username);
            });
        },

        subscribeToMessages(){
            this.client.subscribe("/app/chat.message", (response) => {
                this.messages = JSON.parse(response.body).reverse();
            });
            this.client.subscribe("/chat.message", (response) => {
                this.messages.push(JSON.parse(response.body));
            })
        },

        subscribeToError(){
            console.log(this.currentUser);
            this.client.subscribe(`/${this.currentUser.username}/chat.error`, (response) => {
                this.error = response.body;
            });
        },

        send(){
            this.client.send("/app/chat.message", {}, JSON.stringify({content: this.message}));
            this.error = "";
        },

        scrollDownChat(){
            let chat = document.getElementById("chat-window");
            if (chat != null) {
                chat.scrollTop = chat.scrollHeight;
            }
        }
    }
});