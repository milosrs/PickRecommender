export class RealmList {
    public list;

    constructor() {
        this.list = {
            "Brazil" : "br1",
            "Europe Nordic & East" : "eun1",
            "Europe West" : "euw1",
            "Latin America North" : "la1",
            "Latin America South" : "la2",
            "North America" : "na1",
            "Oceania" : "oc1",
            "Russia" : "ru",
            "Turkey" : "tr1",
            "Japan" : "jp1",
            "Republic of Korea" : "kr"
        };
    }

    public transformToObjectList() {
        let transformed = [];

        for(let key in this.list) {
            transformed.push({realmFull: key, realmShort: this.list[key]});
        }

        return transformed;
    }

}
