import styled from "styled-components";

export const Wrapper = styled.a`
  background: white;
  box-shadow: 0 13px 27px -5px hsl(240deg 30% 28% / 25%),
    0 8px 16px -8px hsl(0deg 0% 0% / 30%), 0 -6px 16px -6px hsl(0deg 0% 0% / 3%);

  &:hover {
    cursor: pointer;
    opacity: 0.7;
  }
`;

export const ImageContainer = styled.div`
  margin:1.5rem;
`;

export const Title = styled.div`
  margin: 2rem;
  text-align: center;
`;
