import reducer, { initialState } from '../../../src/store/ducks/destinations/reducer';
import { ns } from '../../../src/store/ducks/destinations/actions';
import { mockData } from '../../../__mocks__/mockData';

describe(`${ns} reducer`, () => {
    it(`should "${ns}/getDestinationsSuccess"`, () => {
        const startAction = {
            type: `${ns}/getDestinationsSuccess`,
            payload: {
                data: mockData.mockDestinations,
            },
        };

        const expectation = {
            ...initialState,
            destinationList: mockData.mockDestinations.results,
            pageNumber: mockData.mockDestinations.pageNumber,
            pageSize: mockData.mockDestinations.pageSize,
            total: mockData.mockDestinations.total
        };

        expect(reducer({ ...initialState}, startAction)).toEqual(expectation);
    });
});
