import { screen } from '@testing-library/react';

import Header from '../../src/components/Header';
import { render } from '../../test.render';

describe('Header component',  () => {
  it('should render', async () => {
    render(<Header />);
    expect(screen.queryByTestId('goCityLogo')).toBeInTheDocument();
  });
});