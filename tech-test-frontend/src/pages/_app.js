
import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { wrapper } from '../store/configureStore';
import { destinationActions } from '../store/ducks/destinations/actions';
import '../styles/globals.css'

const MyApp = ({ Component, pageProps }) => {
  const dispatch = useDispatch()

  useEffect(() => {
    dispatch(destinationActions.getDestinations())
  }, [])

  return <Component {...pageProps} />
}



export default wrapper.withRedux(MyApp);