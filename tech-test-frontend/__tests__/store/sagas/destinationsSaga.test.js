import { expectSaga } from 'redux-saga-test-plan';
import * as matchers from 'redux-saga-test-plan/matchers';
import { destinationActions } from '../../../src/store/ducks/destinations/actions';
import {
    getDestinations,
} from '../../../src/store/sagas/destinationsSaga';
import { getData } from '../../../src/utils/request';
import { mockData } from '../../../__mocks__/mockData';

jest.mock('next/router', () => ({ push: jest.fn(), replace: jest.fn() }));

describe('Expect DestinationsSaga', () => {
    it('gets destinations', () => {
        const res = mockData.mockDestinations;
        expectSaga(getDestinations)
            .provide([[matchers.call.fn(getData), res]])
            .put({ type: destinationActions.getDestinations.type, payload: { data: res } })
            .run();
    });
});
