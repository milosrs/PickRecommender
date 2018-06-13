import { HelperFunctions } from "../shared/util/helper-functions";

export class ChampionPicks {
    constructor(public friendlyChampions: any, public opponentChampions: any) {
        if(HelperFunctions.isEmptyValue(friendlyChampions)) {
            this.friendlyChampions = [];
        } else {
            this.friendlyChampions = friendlyChampions;
        }
        
        if(HelperFunctions.isEmptyValue(opponentChampions)) {
            this.opponentChampions = [];
        } else {
            this.opponentChampions = opponentChampions;
        }
    }
}