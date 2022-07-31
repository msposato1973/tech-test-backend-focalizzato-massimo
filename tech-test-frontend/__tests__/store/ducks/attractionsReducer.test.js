import reducer, { initialState } from '../../../src/store/ducks/attractions/reducer';
import { ns } from '../../../src/store/ducks/attractions/actions';
import { mockAttractions } from '../../../__mocks__/mockData';

describe(`${ns} reducer`, () => {
    it(`should "${ns}/getAttractionsSuccess"`, () => {
        const startAction = {
            type: `${ns}/getAttractionsSuccess`,
            payload: {
                data: mockAttractions,
            },
        };

        const expectation = {
            ...initialState,
            attractionList: mockAttractions,
        };

        expect(reducer({ ...initialState}, startAction)).toEqual(expectation);
    });
});
