import {ListItem} from '../model/list-item';
import {Request} from '../model/request';

export class HelperFunctions {
  public static isEmptyValue(toCheck: any): boolean {
    let isEmpty = false;

    isEmpty = toCheck === null || toCheck === undefined;

    if (typeof toCheck === 'string' && isEmpty !== true) {
      isEmpty = toCheck.length <= 0 || toCheck === '';
    } else if (typeof toCheck === 'object' && !Array.isArray(toCheck) && isEmpty !== true) {
      isEmpty = Object.keys(toCheck).length <= 0;
    } else if (typeof toCheck === 'object' && Array.isArray(toCheck) && isEmpty !== true) {
      isEmpty = toCheck.length <= 0;
    }

    return isEmpty;
  }

  public static containsEmptyValues(toCheck: any): boolean {
    let hasEmpty = false;

    hasEmpty = toCheck === null || toCheck === undefined;

    if (Array.isArray(toCheck)) {
      for (let i = 0; i < toCheck.length; i++) {
        hasEmpty = this.isEmptyValue(toCheck[i]);
        if (hasEmpty === true) {
          break;
        }
      }
    } else if (typeof toCheck === 'object') {
      for (const key in toCheck) {
        if (toCheck.hasOwnProperty(key)) {
          hasEmpty = this.isEmptyValue(toCheck[key]);
          if (hasEmpty === true) {
            break;
          }
        }
      }
    }

    return hasEmpty;
  }

  public static sortArrayByKey(array, key) {
    return array.sort((a, b) => {
      if (typeof a[key] === 'string') {
        if (a[key].toLowerCase() < b[key].toLowerCase()) {
          return -1;
        } else if (a[key].toLowerCase() > b[key].toLowerCase()) {
          return 1;
        }
        return 0;
      } else {
        if (a[key] < b[key]) {
          return -1;
        } else if (a[key] > b[key]) {
          return 1;
        }
        return 0;
      }
    })
  }

  private static makeRandom() {
    let text = '';
    const possible = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

    for (let i = 0; i < 5; i++) {
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    }

    return text;
  }

  public static createDummyTest(dummyEntity) {
    const ret = [];

    for (let i = 0; i < 20; i++) {
      const text = this.makeRandom();
      const item = new ListItem('assets/aaa.jpg', text, dummyEntity);

      ret.push(item);
    }

    return ret;
  }

  public static createListItems(arrayOfItems, imageKey, textKeys: Array<any>): ListItem[] {
    const ret = [];

    if (!this.isEmptyValue(arrayOfItems)) {
      for (let i = 0; i < arrayOfItems.length; i++) {
        let text = '';

        if(!this.isEmptyValue(textKeys)) {
          for (let j = 0; j < textKeys.length; j++) {
            text += arrayOfItems[i][textKeys[j]] + ' ';
          }
        } else {
            text += arrayOfItems[i] + ' ';
        }

        text = text.trim();
        const item = !this.isEmptyValue(imageKey) ? new ListItem(arrayOfItems[i][imageKey], text, arrayOfItems[i])
                                       : new ListItem(null, text, arrayOfItems[i]);
        ret.push(item);
      }
    }

    return ret;
  }

  public static createRequestItems(arrayOfItems, textKeys: Array<any>, acfn, decfn, type): Request[] {
    const ret = [];

    if (!this.isEmptyValue(arrayOfItems)) {
      for (let i = 0; i < arrayOfItems.length; i++) {
        let text = '';

        for (let j = 0; j < textKeys.length; j++) {
          text += arrayOfItems[i][textKeys[j]] + ' ';
        }

        text = text.trim();
        const item = new Request(text, type, arrayOfItems[i], acfn, decfn);
        ret.push(item);
      }
    }

    return ret;
  }

  public static deleteItemFromArray(arrayOfItems, item) {
    return arrayOfItems = arrayOfItems.splice(arrayOfItems.indexOf(item), 1);
  }
}
