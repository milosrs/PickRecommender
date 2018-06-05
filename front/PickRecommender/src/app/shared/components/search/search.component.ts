import {Component, OnInit, Input, EventEmitter, Output, OnChanges, SimpleChanges} from '@angular/core';
import { HelperFunctions } from '../../util/helper-functions';
import {ListItem} from '../../model/list-item';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Input() public shouldHaveList: boolean;
  @Input() public criteriaKeys: Array<any>;    // Niz kljuceva nekog objekta po kom ce se vrsiti pretraga (moze biti kombinacija kljuceva)
  @Input() public items: Array<any>;           // DTO objekti koje dobijamo nekako sa servera
  @Input() public displayKeys: Array<any>;     // Koje atribute u objektu item treba prikazati
  @Input() public header: string;
  @Input() public requestType: string;
  @Output() onElementClickEvent: EventEmitter<any> = new EventEmitter();  // callback koji treba izvrsiti na klik elementa
  private searchResults = [];
  private selectedCriteria = undefined;
  private criteriaArray = [];
  private text: string;
  private shouldShowAutocomplete: boolean;

  constructor() { }

  ngOnInit() {
    this.shouldShowAutocomplete = false;
    console.log(this.criteriaKeys);
    this.selectedCriteria = this.criteriaKeys[0];

    if (!HelperFunctions.isEmptyValue(this.criteriaKeys)) {
      for (let i = 0; i < this.criteriaKeys.length; i++) {
        const criteria = this.criteriaKeys[i];

        if (typeof criteria !== 'string') {
          let critString = '';
          for (let j = 0; j < criteria.length; j++) {
            if (j === criteria.length - 1) {
              critString += criteria[j];
            } else {
              critString += criteria[j] + ' and ';
            }
          }
          this.criteriaArray.push({
            'view': critString,
            'critKeys': criteria
          });
        } else {
          this.criteriaArray.push({
            'view': criteria,
            'critKeys': criteria
          })
        }
      }
    }
  }

  elementClicked(result) {
    this.onElementClickEvent.emit(result);

    if (!this.shouldHaveList) {
      this.text = this.searchResultToString(result);
    }
    this.shouldShowAutocomplete = false;
  }

  onInputChange(event) {
    this.searchResults = [];
    this.shouldShowAutocomplete = true && !this.shouldHaveList;

    if (!HelperFunctions.isEmptyValue(this.selectedCriteria)) {
      this.filterReults();
    }
  }

  shouldAddToResults(comparator, itemToAdd) {
    let ret = null;

    if (typeof comparator === 'string') {
      ret = comparator.includes(this.text);
    } else if (typeof comparator === 'number') {
      ret = comparator === parseInt(this.text, 10)
    }

    return ret && this.searchResults.indexOf(itemToAdd) === -1;
  }

  searchResultToString(result) {
    let ret = '';

    for (let i = 0; i < this.displayKeys.length; i++) {
      ret += result[this.displayKeys[i]].toString() + ' ';
    }

    ret = ret.trim();

    return ret;
  }

  filterReults() {
    if (!HelperFunctions.isEmptyValue(this.items)) {
      for (let i = 0; i < this.items.length; i++) {
        const toAdd = {};
        const item = this.items[i];
        toAdd['relatedItem'] = item;
        for (let k = 0; k < this.displayKeys.length; k++) {
          const key = this.displayKeys[k];
          toAdd[key] = item[key];
          toAdd['index'] = i;
        }

        const critKeys = this.selectedCriteria.split('and');
        if (typeof critKeys !== 'string') {
          for (let z = 0; z < critKeys.length; z++) {
            critKeys[z] = critKeys[z].trim();
          }
          for (let j = 0; j < critKeys.length; j++) {
            if (this.shouldAddToResults(item[critKeys[j]], toAdd[critKeys[j]])) {
              this.searchResults.push(toAdd);
            }
          }
        } else {
          if (this.shouldAddToResults(item[this.selectedCriteria], toAdd)) {
            this.searchResults.push(toAdd);
          }
        }
      }
    }
  }

  allToView() {
    const ret = [];

    if (!HelperFunctions.isEmptyValue(this.searchResults)) {
      for (let i = 0; i < this.searchResults.length; i++) {
        ret.push(this.searchResultToString(this.searchResults[i]));
      }
    } else if (HelperFunctions.isEmptyValue(this.text) && !HelperFunctions.isEmptyValue(this.items)) {
      for (let i = 0; i < this.items.length; i++) {
        ret.push(this.searchResultToString(this.items[i]));
      }
    }

    return ret;
  }

  criteriaChange(event) {
    this.searchResults = [];
    this.shouldShowAutocomplete = true && !this.shouldHaveList;

    if (!HelperFunctions.isEmptyValue(this.selectedCriteria)) {
      this.filterReults();
    }
  }

  createListItems() {
    const ret: ListItem[] = [];
    const imgInfo = this.isContainingImage();

    if (!HelperFunctions.isEmptyValue(this.searchResults)) {
      for (let i = 0; i < this.searchResults.length; i++) {
        if (imgInfo.CONTAINS) {
          const imgKey = this.searchResults[i].keys[imgInfo.INDEX];
          const item: ListItem = new ListItem(this.searchResults[i][imgKey],
                                              this.searchResultToString(this.searchResults[i]),
                                              this.searchResults[i]['relatedItem']);
          ret.push(item);
        } else {
          const item: ListItem = new ListItem(null,
                                                        this.searchResultToString(this.searchResults[i]),
                                                        this.searchResults[i]['relatedItem']);
          ret.push(item);
        }
      }
    } else if (HelperFunctions.isEmptyValue(this.text) && !HelperFunctions.isEmptyValue(this.items)) {
      for (let i = 0; i < this.items.length; i++) {
        if (imgInfo.CONTAINS) {
          const imgKey = this.items[i].keys[imgInfo.INDEX];
          const item: ListItem = new ListItem(this.items[i][imgKey],
                                              this.searchResultToString(this.items[i]),
                                              this.items[i]);
          ret.push(item);
        } else {
          const item: ListItem = new ListItem(null,
                                              this.searchResultToString(this.items[i]),
                                              this.items[i]);
          ret.push(item);
        }
      }
    }

    return ret;
  }

  isContainingImage() {
    let containsImg = false;
    let keyIndex = null;
    let i = 0;
    let ret = {
      CONTAINS: false,
      INDEX: null
    };

    if (!HelperFunctions.isEmptyValue(this.items)) {
      for (let key in this.items[0].keys) {
        if (this.items[0].hasOwnProperty(key)) {
          key = key.toLowerCase();
          if (key.indexOf('path') > -1) {
            containsImg = true;
            keyIndex = i;
            break;
          }
        }
        i++;
      }
      ret = {
        CONTAINS: containsImg,
        INDEX: keyIndex
      }
    }
    return ret;
  }
}
