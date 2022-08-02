import { Link, useNavigate } from "react-router-dom"
import './NotFoundPage.css'
import React from 'react'
import { useAuthState } from '../../atoms';
function NotFoundPage() {
    const [currentUser, setCurrentUser] = useAuthState();

    const navigate = useNavigate();

    return (
        <div className='NotFoundPage'>
            not found

        </div>
    )
}

export default NotFoundPage