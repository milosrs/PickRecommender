export class Message {
    public messageType: string;
    public content: string;
    public loggedUserName : string;

    constructor(messageType, content, loggedUserName: string) {
        this.messageType = messageType;
        this.content = content;
        this.loggedUserName = loggedUserName;
    }
}
