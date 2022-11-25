import { NgModule } from "@angular/core";
import { HeroesComponent } from "./heroes.component";


@NgModule({
    declarations: [
        HeroesComponent
    ],
    providers: [],
    bootstrap: [HeroesComponent],
    exports: [HeroesComponent],
})
export class HeroesModule{
}