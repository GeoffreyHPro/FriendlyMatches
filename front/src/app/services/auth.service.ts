
import { Injectable } from '@angular/core';
import { StateService } from './state.service';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private appState: StateService) { }


  login(username: string, password: string) {

    //let resp: any = await firstValueFrom(this.http.get("https://localhost:8080/" + username));
    let resp: any =
    {
      token: "fefefef",
      username: "geoffrey"
    }

    if (username == resp.username) {
      this.appState.setAuthState({
        username: username,
        roles: "USER"
      });
      return true
    } else {
      return false
    }
  }
}
