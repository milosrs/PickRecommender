import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Constants} from '../../constants/constants';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {

  public types = Constants.RequestType;
  @Input() private type: string;
  @Input() private requestText: string;
  @Input() relatedItem: any;
  private activeAction: string;
  private dynamicClass: string;
  @Output() acceptClickEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() declineClickEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() addRemoveClickEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
    console.log(this.relatedItem);
    this.activeAction = "Add";
  }

  accept(event) {
    if (this.relatedItem == null) {
      this.acceptClickEvent.emit(null);
    } else {
      this.acceptClickEvent.emit(this.relatedItem);
    }
  }

  decline(event) {
    if (this.relatedItem == null) {
      this.declineClickEvent.emit(null);
    } else {
      this.declineClickEvent.emit(this.relatedItem);
    }
  }

  singleAction(event) {
    if (this.activeAction === "Add") {
      this.activeAction = "Remove"
      this.dynamicClass = "btn btn-success";
    } else if(this.activeAction === "Remove") {
      this.activeAction = "Add";
      this.dynamicClass = "btn btn-danger";
    }
    this.addRemoveClickEvent.emit(this.relatedItem);
  }

  resetButtons() {
    this.activeAction = 'Add';
    this.dynamicClass = "btn btn-success";
  }
}
