import React from 'react'
import './AuthPageTitle.css'
function AuthPageTitle(props) {
    return (
        <div className='title-container'>
            <div className='title'>{ props.title}</div>
        </div>
    )
}

export default AuthPageTitle