import { Injectable } from '@angular/core';
import {ApiService} from "./api.service";
import {ConfigService} from "./config.service";

@Injectable()
export class StringStatsService {

  PHRASE_REG = /[^;,.]+[;,.]?/ig;//updated to include the remainder 'good; not as good before'
  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  getStats(input: string) {
    return this.apiService.post(this.config.stats_url,input);
  }

  /*
  * Breaks down a string to reverse sentence order,
   * Breaks sentences into phrases to be further reversed
  * */
  reverse(input: string) {
    if (input) {//if not (null, undefined, or empty)
      let lines = input.split('\n');
      let output = [];
      lines.forEach((sentence) => {
        let phrases = [];
        const matches = sentence.match(this.PHRASE_REG);
        if (matches) {
          matches.forEach((phrase) => {
            phrases.push(this.reversePhrase(phrase))
          });
        } else {//no phrase!!! still might be a word or group of words in first line though
          phrases.push(this.reversePhrase(sentence));
        }
        output.push(phrases.join(''));
      });
      return output.reverse().join('\n');
    }
  }

  /*
  * Takes input as string that should end in ';', ',', or '.')
  * */
  reversePhrase(input: string) {
    let reversedPhrase = "";
    if (input) {//if not (null, undefined, or empty)
      let cutOff = input.length - 1;//position of last character and length of string after pluck
      let delim = "";
      let theRest = input;
      if (input[cutOff].match(/[;,.]/)) {//truthy or null
        delim = input[cutOff];// pluck our delimiter
        theRest = input.substring(0, cutOff);//drop delim from the string to be processed, to put it back in the right place later
      } else {
        cutOff = input.length; //no delim input not touched
      }
      theRest = theRest.trim();// leave spaces in the right place as well
      const leadSpace = cutOff - theRest.length;//I think the way it's set up we are only trimming from the left
      reversedPhrase = " ".repeat(leadSpace) + theRest.split(' ').reverse().join(' ') + delim;
    }
    return reversedPhrase;
  }
}
