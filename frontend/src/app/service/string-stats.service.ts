import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {ConfigService} from "./config.service";

@Injectable()
export class StringStatsService {

  PHRASE_REG = /[^;,.]+[;,.]/ig;
  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getStats(input: string) {
    return this.apiService.post(this.config.stats_url,input);
  }

  reverse(input: string) {
    let lines = input.split('\n');
    let output = [];
    lines.forEach((sentence)=>{
      let phrases = [];
      const matches = sentence.match(this.PHRASE_REG);
      if(matches){
        matches.forEach((phrase) => {
          phrases.push(this.reversePhrase(phrase))
        });
      }
      output.push(phrases.join(''));
    });
    return output.reverse().join('\n');
  }

  reversePhrase(input: string) {
    const cutOff = input.length-1;
    const delim = input[cutOff];
    let theRest = input.substring(0,cutOff);
    theRest = theRest.trim();
    const leadSpace = cutOff - theRest.length;//I think the way it's set up we are only trimming from the left
    return " ".repeat(leadSpace)+ theRest.split(' ').reverse().join(' ')+delim;
  }
}
