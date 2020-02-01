import { Authority } from './authority.model';


export class UserModelRegister {
    id: string;
    name: string;
    surname: string;
    username: string;
    email: string;
    password: string;
    enabled: boolean;
    authorities: Set<Authority>;
    role : string;

    constructor(){
      
    }
  }