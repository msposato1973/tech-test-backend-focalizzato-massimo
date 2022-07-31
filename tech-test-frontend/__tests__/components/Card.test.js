import { screen } from '@testing-library/react';

import Card from '../../src/components/Card';
import { render } from '../../test.render';
import { mockData } from '../../__mocks__/mockData';


const defaultProps = {
  destinationID: mockData.mockDestinations.results[0].id,
  destination: mockData.mockDestinations.results[0].name,
  imageSrc: mockData.mockDestinations.results[0].imageUrl,
}
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

describe('Card component',  () => {
  beforeEach(() => {
    useRouter.mockImplementation(() => ({
        route: '/',
        pathname: '/',
        query: '',
        asPath: '',
    }));
  });

  it('should render', async () => {
    render(<Card {...defaultProps}/>);
    expect(screen.getByTestId('cardComponent')).toBeInTheDocument();
    expect(screen.getByTestId('card-image')).toBeInTheDocument();
    expect(screen.getByText(defaultProps.destination)).toBeInTheDocument();
  });

});