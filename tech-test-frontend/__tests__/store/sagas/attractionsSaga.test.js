import { expectSaga } from 'redux-saga-test-plan';
import * as matchers from 'redux-saga-test-plan/matchers';
import { attractionActions } from '../../../src/store/ducks/attractions/actions';
import {
    getAttractions,
} from '../../../src/store/sagas/attractionsSaga';
import { getData } from '../../../src/utils/request';
import { mockData } from '../../../__mocks__/mockData';

jest.mock('next/router', () => ({ push: jest.fn(), replace: jest.fn() }));

describe('Expect AttractionsSaga', () => {
    it('gets attractions', () => {
        const res = mockData.mockAttractions;
        expectSaga(getAttractions, { payload:  'some-id' })
            .provide([[matchers.call.fn(getData), res]])
            .put({ type: attractionActions.getAttractions.type, payload: { data: res } })
            .run();
    });
});
