import { atom, useRecoilState } from 'recoil'


const AuthState = atom({
    key: "AuthState",
    default: { isLoggedIn: false, userData: {} }
})

export const useAuthState = () => useRecoilState(AuthState);

