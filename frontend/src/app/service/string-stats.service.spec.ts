import { TestBed, inject } from '@angular/core/testing';

import { StringStatsService } from './string-stats.service';
import {ApiService} from "./api.service";
import {ConfigService} from "./config.service";
import {MockApiService} from "./mocks";

describe('StringStatsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StringStatsService, {
        provide: ApiService,
        useClass: MockApiService
      }, ConfigService]
    });
  });

  it('should be created', inject([StringStatsService], (service: StringStatsService) => {
    expect(service).toBeTruthy();
  }));

  it('should reverse special', inject([StringStatsService], (service: StringStatsService) => {
    const testString = "This exercise can be quite challenging; take your time.\n" +
      "If you're not careful, it can really do you in. But don't worry.\n" +
      "This fun challenge shows how you, the developer, solve problems.";
    const result = "you how shows challenge fun This, developer the, problems solve.\n" +
      "careful not you're If, in you do really can it. worry don't But.\n" +
      "challenging quite be can exercise This; time your take.";
    expect(service.reverse(testString)).toEqual(result);
  }))
});
