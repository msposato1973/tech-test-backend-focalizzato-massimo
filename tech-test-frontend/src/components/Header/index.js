import React from "react";
import Link from "next/link";
import { Wrapper, Container, Logo } from "./styles";

const Header = () => (
  <Wrapper>
    <Container>
      <Link href="/">
        <a>
          <Logo src="/assets/img/logo.svg" alt="Go City Logo" data-testid="goCityLogo" />
        </a>
      </Link>
    </Container>
  </Wrapper>
);

export default Header;
