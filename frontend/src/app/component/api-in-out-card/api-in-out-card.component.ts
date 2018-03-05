import {Component, Input, OnInit} from '@angular/core';
import {ApiCardComponent} from "../api-card";

@Component({
  selector: 'app-api-in-out-card',
  templateUrl: './api-in-out-card.component.html',
  styleUrls: ['./api-in-out-card.component.css', '../api-card/api-card.component.scss']
})
export class ApiInOutCardComponent extends ApiCardComponent {

  private _responseObj = {};
  @Input()
  isLocal = false;

  @Input()
  set responseObj(obj:any){
    //TODO extract ouput field
    if(this.isLocal){
      this.output = obj.data;
    }
    this._responseObj = obj;
  }
  get responseObj(): any {
    return this._responseObj;
  }

  input: string;
  output: string;

  onButtonClick() {
    this.expand = !this.isLocal;
    const { apiText, input } = this;
    this.apiClick.next({apiText,input}); //todo add typed object
  }
}
