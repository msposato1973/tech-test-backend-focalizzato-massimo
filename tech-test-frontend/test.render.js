import React from 'react';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import { render as defaultRender } from '@testing-library/react';
import { RouterContext } from 'next/dist/shared/lib/router-context';
import { rootReducer } from './src/store/ducks/index';
import { mockRouter } from './__mocks__/NextRouter';

const customRender = (
    ui,
    { initialState = {}, store = createStore(rootReducer, initialState), ...options } = {},
    { router } = {}
) => {
    const Providers = ({ children }) => (
        <Provider store={store}>
                <RouterContext.Provider value={{ ...mockRouter, ...router }}>
                    {children}
                </RouterContext.Provider>
        </Provider>
    );

    return defaultRender(ui, { wrapper: Providers, ...options });
};

// re-export everything
export * from '@testing-library/react';

// override render method
export { customRender as render };
