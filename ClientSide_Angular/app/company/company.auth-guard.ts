import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';

@Injectable({providedIn:'root'})

export class CompanyAuthGuard implements CanActivate{
    constructor(private authService: AuthService, private router: Router){}
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean>| Promise<boolean>{
    
        return this.authService.user.pipe(take(1),map(user=>{
            if(user!=null){
                const isAuth=user.authority;
                if(isAuth=='ROLE_COMPANY'){
                    return true;
            }
        
            }
            this.router.navigate(['/products'])
        }))
        
         }
}