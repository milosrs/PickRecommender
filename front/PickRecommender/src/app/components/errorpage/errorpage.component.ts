import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-errorpage',
  templateUrl: './errorpage.component.html',
  styleUrls: ['./errorpage.component.css']
})
export class ErrorpageComponent implements OnInit {


  @Input()
  private code: number;

  @Input()
  private text: string;
  
  constructor() { }

  ngOnInit() {
  }

}
