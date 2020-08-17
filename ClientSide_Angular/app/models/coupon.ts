

export class Coupon{
    constructor(
        public id?:number,
        public companyId?:number,
        public title?:string,
        public startDate?:Date,
        public endDate?:Date,
        public amount?:number,
        public type?:string,
        public message?:string,
        public price?:number,
        public image?:string,
        public avaliable?:boolean
        ){ 
    }
    
    // public getType():string{
    //     return this.type;
    // }

}