import { screen } from '@testing-library/react';

import * as ReactRedux from 'react-redux';
import DestinationPage from '../../src/pages/destination/[destinationID].js';
import { render } from '../../test.render';
import { mockData } from '../../__mocks__/mockData';

const mockDispatch = jest.fn();
jest.mock('next/router', () => ({
    useRouter() {
        return {
            route: '',
            pathname: '',
            query: '',
            asPath: '',
        };
    },
}));
const useRouter = jest.spyOn(require('next/router'), 'useRouter');

describe('Destination Page',  () => {
  beforeEach(() => {
    ReactRedux.useDispatch = jest.fn().mockImplementation(() => mockDispatch);
  });

  afterEach(() => {
    mockDispatch.mockClear();
  });

  it('should render with no data', async () => {
    render(<DestinationPage />);
    expect(screen.getByText('Total attractions: 0')).toBeInTheDocument();
    expect(screen.queryByTestId('attractionItem')).toBeFalsy();
  });

  it('should render with destinations', async () => {
    render(<DestinationPage />, {
      initialState: {
        destination: {
          destinationList: mockData.mockDestinations.results
        },
        attraction: {
          attractionList: mockData.mockAttractions
        }
      }
    });
    expect(screen.getByText('Total attractions: ' + mockData.mockAttractions.length)).toBeInTheDocument();
    expect(screen.getAllByTestId('attractionItem')).toHaveLength(mockData.mockAttractions.length);
    expect(screen.getByText(mockData.mockAttractions[0].name + ' - ' + mockData.mockAttractions[0].visitPercentage + '%')).toBeInTheDocument();
  });
});