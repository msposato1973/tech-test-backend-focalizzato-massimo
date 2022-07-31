import styled from "styled-components";

export const Wrapper = styled.header`
  display: flex;
  width: 100%;
  align-content: center;
`;

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 4.6875rem;
  margin: 0 auto;
  padding: 0 1rem;
`;

export const Logo = styled.img`
  height: 3.125rem;
  vertical-align: top;

  @media (min-width: 640px) {
    height: 3.75rem;
  }
`;
