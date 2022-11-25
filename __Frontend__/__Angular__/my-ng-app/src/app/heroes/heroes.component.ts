import { Component, OnInit } from "@angular/core";

@Component({
    selector: "heroes",
    templateUrl: "./heroes.component.html",
    styleUrls: ["./heroes.component.scss"],
})
export class HeroesComponent implements OnInit{
    constructor () {
    }
    ngOnInit(): void {
        console.log("create component heroes");
    }
}