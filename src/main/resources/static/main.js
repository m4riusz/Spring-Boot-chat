/**
 * Created by mariusz on 09.02.17.
 */
new Vue({
    el: '#main',
    data: {
        welcome: "Welcome to simple chat application",
        connected: false,
        client: {},
        users: []
    },
    methods: {
        connect: function () {
            let websocket = new SockJS("/ws");
            this.client = Stomp.over(websocket);
            this.client.connect({}, (frame) => {
                this.connected = true;
                this.subscribeToUsers();
            });
        },

        disconnect: function () {
            this.client.send("/app/users.del", {}, "");
            this.client.disconnect(() => {
                this.connected = false;

            });

        },

        subscribeToUsers: function () {
            this.client.subscribe("/app/users.all", (response) => {
                this.users = JSON.parse(response.body);
            });
            this.client.subscribe("/users.new", (response) => {
                this.users.push(JSON.parse(response.body));
            });
            this.client.subscribe("/users.del", (response) => {
                this.users = this.users.filter(user => user.username != JSON.parse(response.body).username);
            });
            this.client.send("/app/users.new", {}, "");
        }
    }
});