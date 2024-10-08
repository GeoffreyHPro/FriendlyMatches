import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StateService {
  public authState : any = {
    username : undefined,
    roles: undefined,
    isAuthenticated: false,
    token : undefined
  }

  constructor() { }

  public setAuthState(state : any){
    this.authState = {...this.authState, ...state};
  }
}
