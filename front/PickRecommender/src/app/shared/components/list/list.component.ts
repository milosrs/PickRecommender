import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild, ViewChildren, QueryList, NgModule} from '@angular/core';
import {HelperFunctions} from '../../util/helper-functions';
import {Constants} from '../../constants/constants';
import {ListItem} from '../../model/list-item';
import { RequestComponent } from '../request/request.component';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  private listType = Constants.ListType;
  private requestType = Constants.RequestType;
  @Output() onElementClickEvent: EventEmitter<any> = new EventEmitter();
  @Input() public header: string;
  @Input() public items: any;
  @Input() public type: string;
  @Input() public selectedRequestType: string;
  @Input() public dynamicStyle: string;
  @Output() acceptClickEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() declineClickEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() addRemoveClickEvent: EventEmitter<any> = new EventEmitter<any>();
  @ViewChildren(RequestComponent) reqComps: QueryList<RequestComponent>;

  constructor() { }

  ngOnInit() {console.log(this.dynamicStyle); }

  elementClicked(item) {
    this.onElementClickEvent.emit(item);
  }

  accept(object) {
    this.acceptClickEvent.emit(object);
  }

  decline(object) {
    this.declineClickEvent.emit(object);
  }

  addRemove(object) {
    this.addRemoveClickEvent.emit(object);
  }

  resetChildren() {
    this.reqComps.forEach(c =>{
      console.log(c);
      c.resetButtons();
    } );
  }
}
