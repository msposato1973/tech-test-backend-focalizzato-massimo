import { screen } from '@testing-library/react';

import Home from '../../src/pages';
import { render } from '../../test.render';
import { mockData } from '../../__mocks__/mockData';

describe('Homepage',  () => {
  it('should render with no data', async () => {
    render(<Home />);
    expect(screen.getByText('Destinations')).toBeInTheDocument();
    expect(screen.queryByTestId('cardComponent')).toBeFalsy();
  });

  it('should render with destinations', async () => {
    render(<Home />, {initialState: {
      destination: {
        destinationList: mockData.mockDestinations.results
      }
    }});
    expect(screen.getByText('Destinations')).toBeInTheDocument();
    expect(screen.getAllByTestId('cardComponent')).toHaveLength(mockData.mockDestinations.results.length);
  });
});