export class Request {
  constructor(public text: string,
              public type: string,
              public relatedItem: any,
              public acceptFn: any,
              public declineFn: any) {}
}
