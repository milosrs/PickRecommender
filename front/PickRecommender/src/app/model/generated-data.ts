import { Champion } from "./champion";
import { SummmonerSpell } from "./summmoner-spell";

export class GeneratedData {
    constructor(public champRecommendations: Champion[],
                public spellRecommendations: SummmonerSpell[]) {}
}
