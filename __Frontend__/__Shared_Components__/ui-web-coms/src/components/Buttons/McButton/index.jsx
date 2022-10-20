import React from 'react'
import "./mcButton.css"

const STYLES = [
    "btn--primary--solid",
    "btn--warning--solid",
    "btn--danger--solid",
    "btn--success--solid",
    "btn--primary--outline",
    "btn--warning--outline",
    "btn--danger--outlined",
    "btn--success--outline"
]

const SIZES = [
    "btn--medium", "btn--small", "btn--fwidth-fheight", "btn--fwidth-sheight", "btn--fwidth-mheight", "btn--swidth-fheight", "btn--mwidth-fheight"
]

const McButton = ({children, type, onClick, buttonStyle, buttonSize}) => {
    const inputButtonStyle = STYLES.includes(buttonStyle) ? buttonStyle : STYLES[0]
    const inputButtonSize = SIZES.includes(buttonSize) ? buttonSize : SIZES[0]

    return (
        <button className={`mcbtn ${inputButtonStyle} ${inputButtonSize}`} onClick={onClick} type={type}>{children}</button>
    )
}

export default McButton