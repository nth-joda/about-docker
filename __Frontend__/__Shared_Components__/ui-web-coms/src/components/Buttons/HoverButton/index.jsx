import React from "react";
import "./hoverButton.css";

const TYPES = ["solid-up", "solid-down", "transparent-up", "transparent-down"];
const HvButton = (props) => {
  const type = props.type && TYPES.includes(props.type) ? props.type : TYPES[0];
  return <button className={`hv-btn hv-btn--${type}`}>{props.children}</button>;
};

export default HvButton;
