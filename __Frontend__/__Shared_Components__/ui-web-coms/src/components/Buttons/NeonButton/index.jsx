import React from "react";
import "./neonButton.css";

const REFERENCE = "https://www.youtube.com/watch?v=6xNcXwC6ikQ";

const NnButton = (props) => {
  return <button className="nn-btn">{props.children}</button>;
};

export default NnButton;
