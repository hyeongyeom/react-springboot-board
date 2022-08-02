import React from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { useAuthState } from '../../atoms';
import './HeaderLink.css'
function HeaderLink() {
    const [currentUser, setCurrentUser] = useAuthState();
    const logout = () => {
        setCurrentUser({ isLoggedIn: false, userData: "" })
    }
    return (

        <div className='Head_link'>
            {currentUser.isLoggedIn ?
                <>
                    <div className='Navbar_link logout' onClick={logout}>로그아웃</div>
                    &nbsp;|&nbsp;
                    <Link to="/mypage"><div className='Navbar_link mypage'>마이페이지</div></Link>
                </>
                :
                <>
                    <Link to={{pathname:"/login",state:"message"} }><div className='Navbar_link'>로그인</div></Link>
                    &nbsp;|&nbsp;
                    <Link to="/register"><div className='Navbar_link'>회원가입</div></Link>
                </>
            }
        </div>
    )
}

export default HeaderLink