export class Participant{
    public participant: string;
    public groupParticipants: string[];
    public groupId : number;
    public groupName : string;

    constructor(participant : string, groupParticipants : string[], groupId : number, groupName : string) {
        this.participant = participant;
        this.groupParticipants = groupParticipants;
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
