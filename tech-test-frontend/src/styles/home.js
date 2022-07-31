import styled from "styled-components";

export const Container = styled.div`
  padding: 0 2rem;
`;

export const Main = styled.main`
  min-height: 100vh;
  padding: 4rem 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.h1`
  margin-top: 0;
  line-height: 1.15;
  font-size: 4rem;
`;

export const Grid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(15rem, 1fr));
  gap: 1.25rem;
`;

export const Error = styled.div`
  color: #DD0000;
  border: 2px solid currentColor;
  border-radius: 3px;
  line-height: 2rem;
  padding: 1rem;
  margin: 1rem 0;
`